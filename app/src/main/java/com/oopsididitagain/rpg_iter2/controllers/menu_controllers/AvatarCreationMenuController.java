package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;


import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.AvatarCreationMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Game;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu;
import com.oopsididitagain.rpg_iter2.models.occupations.Smasher;
import com.oopsididitagain.rpg_iter2.models.occupations.Sneak;
import com.oopsididitagain.rpg_iter2.models.occupations.Summoner;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;


public class AvatarCreationMenuController extends Controller {
	public static AvatarCreationMenuController instance;
	Avatar avatar;
	GameMap gameMap;
	private static AvatarCreationMenu avatarCreationMenu;
	
	private AvatarCreationMenuController(){
		createAvatar();
		createGameMap();
	}
	
	public static AvatarCreationMenuController getInstance() {
		if ( instance == null ){
			avatarCreationMenu = new AvatarCreationMenu();
			instance = new AvatarCreationMenuController();
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = AvatarCreationMenuController.getInstance();

		switch(command){
		case MOVE_EAST:
			avatarCreationMenu.nextOption();
			break;
		case MOVE_WEST:
			avatarCreationMenu.previousOption();
			break;
		case ENTER:
		case USE:
			switch (avatarCreationMenu.getCurrentOption()) {
			case Summoner:
				assignSummoner();
				break;
			case Smasher:
				assignSmasher();
				break;
			case Sneak:
				assignSneak();
				break;
			}
			Game game = new Game(this.avatar);
			GameController gc = GameController.getInstance();
			gc.setGame(game);
			controller = gc;
			break;
		default:
			break;
		}
		return controller;	
	}
	
	private void assignSmasher(){
		this.avatar.setOccupation(new Smasher());
	}
	private void assignSummoner(){
		this.avatar.setOccupation(new Summoner());
	}
	private void assignSneak(){
		this.avatar.setOccupation(new Sneak());
	}
	private void createEntityMapInteraction() {
		// TODO Auto-generated method stub
		
	}
	private void createGameMap() {
		// TODO Auto-generated method stub
		
	}
	private void createAvatar() {
		Position position = new Position(0,0,Direction.SOUTH);
		StatBlob statBlob = new StatBlob(0, 0, 0, 0, 0, 0, 0, 20, 20);
		avatar = new Avatar("avatar", position,statBlob );
		
	}
	private void switchControllers(GameController controller){
		controller.setAvatar(this.avatar);
		controller.setMap(this.gameMap);
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		AvatarCreationMenuViewInteraction avatarCreationMenuViewInteraction = new AvatarCreationMenuViewInteraction(this.avatarCreationMenu);
		return avatarCreationMenuViewInteraction;
	}

}
