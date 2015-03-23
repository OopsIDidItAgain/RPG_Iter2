package com.oopsididitagain.rpg_iter2.models.interaction_classes;

import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public class AvatarEntityInteraction {

	public static void avatarAttack(Avatar avatar, Npc npc) {
		double off = avatar.offensiveRating();
		double def = npc.defensiveRating();

		npc.statBlob().merge(new StatBlob(0, 0, 0, 0, 0, 0, 0, def - off, 0));
		System.out.println("Off: " + off + " Def: " + def + " avatar hp: "
				+ avatar.statBlob().getLifeAmount() + " entity: "
				+ npc.statBlob().getLifeAmount());
	}

	public static void entityAttack(Avatar avatar, Npc npc) {
		double off = npc.offensiveRating();
		double def = avatar.defensiveRating();

		avatar.statBlob()
				.merge(new StatBlob(0, 0, 0, 0, 0, 0, 0, def - off, 0));
		System.out.println("Off: " + off + " Def: " + def + " avatar hp: "
				+ avatar.statBlob().getLifeAmount() + " entity: "
				+ npc.statBlob().getLifeAmount());
	}
}
