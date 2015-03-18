package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JInternalFrame;
import javax.swing.Timer;

import model.Game;
import model.GameLog;
import model.GameMap;
import model.InfluenceSet;
import model.World;
import model.Entity.Avatar;
import model.Skill;
import model.occupation.Occupation;
import view.CombinedGameView;
import view.ControlConfigView;
import view.LevelUpView;
import view.StatisticsView;
import view.SystemMenuView;
import view.View;
 
public class GameController {
	
		private World world;
		private GameMap map;
		private Avatar avatar;
		private boolean reset = false;
        
        private SystemsController systems;
        private StatsController stats;
        private LevelUpController levelUp;
        
        private CombinedGameView combinedGameView;
        
        //private GameLog log = new GameLog();
        
        
        
        public GameController(){
        	//This is needed. DON'T DELETE. We should probably make some temp game here.
        }
        
        public GameController(Game game){
        	this.world = game.getWorld();
        	this.avatar = game.getAvatar();
        	this.map = world.getMap(avatar.getCurrMap());
			combinedGameView = new CombinedGameView(map, avatar, new LevelUPButton(), new SystemsMenuButton(), new StatButtonAction());
        	
        	systems = new SystemsController(combinedGameView, avatar, world);
        	stats = new StatsController(combinedGameView, avatar); 
        	levelUp = new LevelUpController(combinedGameView, avatar);

     		
     		Timer statUpdater = new Timer(100, new StatCheck());
     		statUpdater.start();
        }  
                       
        /********************MISC OPERATIONS**********************/
        
        
        public View getView(){
        	return combinedGameView;
        }
       
        public boolean startReset(){
        	return reset;
        }
        
        public void stopReset(){
            reset  = false;
        }    
                   
        /********************Action Listeners**********************/    
      
        public class SystemsMenuButton implements ActionListener { //Systems
            public void actionPerformed(ActionEvent e) {
            	systems.spawnSystems();
            }
        }     
       
        public class StatButtonAction implements ActionListener {//Statistics
            public void actionPerformed(ActionEvent e) {
                stats.spawnStats();
            }
        }
        
        public class LevelUPButton implements ActionListener {//LevelUP
            
            public void actionPerformed(ActionEvent e) {
            	levelUp.spawnLevelUp();
    			//applyEffect(new RadialInfluenceSet(map, map.getEntityTile(avatar),0,0));
            }
        }
        
    
    public class StatCheck implements ActionListener {
    	private int yourLvl;
    	private String currMap;
    	public StatCheck(){
    		yourLvl = avatar.getStatValue("Level"); 
    		currMap = avatar.getCurrMap();
    	}
		public void actionPerformed(ActionEvent e) {
			if(avatar.getStatValue("Lives") <= 0){
				 reset = true;
				 combinedGameView.setNext("Main");
	             combinedGameView.setRedraw(true);
			}
			else if(avatar.getStatValue("HP") <= 0){
				avatar.setStatValue("Lives", avatar.getStatValue("Lives")-1);
				avatar.setStatValue("HP", avatar.getStatValue("Life"));
			}
			else if(yourLvl != avatar.getStatValue("Level")){
				avatar.setLevels(avatar.getLevels()+avatar.getStatValue("Level")-yourLvl);
				yourLvl = avatar.getStatValue("Level");
			}
			else if(!currMap.equals(avatar.getCurrMap())){
				currMap = avatar.getCurrMap();
				map = world.getMap(currMap);
				combinedGameView.changeMap(map);
			}
			stats.updatetable();
			combinedGameView.updateStatus();
		}
	}
    
    public void applyEffect(InfluenceSet i) {
    	i.printInfluenceSet();
    }
 
       
}
