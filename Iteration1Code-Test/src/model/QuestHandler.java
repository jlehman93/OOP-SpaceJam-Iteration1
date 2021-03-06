package model;

import model.entity.Avatar;
import model.items.Item;
import model.visitor.ItemQuestVisitor;
import model.visitor.OccupationVisitor;

public class QuestHandler {
	private Avatar avatar;
	private ItemQuestVisitor visitor = new ItemQuestVisitor();
	GameMap map;
	
	public QuestHandler(Avatar avatar, GameMap map){
		this.avatar=avatar;
		this.map=map;
		visitor.setAvatar(this.avatar);
	}
	
	public void apply(Tile tile){
		visitor.setTile(tile);
		Item it= tile.getItem();
		
		Decal decal=tile.getDecal();
		Item item =  tile.getItem(); 
		Trap trap= tile.getTrap();
		
		if(decal!=null){
			//violation of LoD
			decal.getAreaEffect().apply(avatar); 
		}
		boolean itemExist = it != null;
		if (itemExist){
			it.accept(this.visitor);
		}
		
	}
	

}
