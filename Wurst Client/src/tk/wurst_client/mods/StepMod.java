/*
 * Copyright � 2014 - 2015 Alexander01998 and contributors
 * All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.mods;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent;
import tk.wurst_client.events.listeners.UpdateListener;
import tk.wurst_client.mods.Mod.Category;
import tk.wurst_client.mods.Mod.Info;
import tk.wurst_client.navigator.settings.SliderSetting;

@Info(category = Category.MOVEMENT,
	description = "Allows you to step up full blocks.",
	name = "Step")
public class StepMod extends Mod implements UpdateListener
{
	public float normalRange = 1F;

	@Override
	public void initSettings()
	{
		settings.add(new SliderSetting("Range", normalRange, 0.5, 9, 1,
				BoundedRangeComponent.ValueDisplay.DECIMAL));
	}

	@Override
	public void onEnable()
	{
		wurst.events.add(UpdateListener.class, this);
	}
	
	@Override
	public void onUpdate()
	{
		if(wurst.mods.yesCheatMod.isActive())
		{
			mc.thePlayer.stepHeight = 0.5F;
			if(mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround)
				mc.thePlayer.jump();
		}else
			mc.thePlayer.stepHeight = isEnabled() ? normalRange : 0.5F;
	}

	@Override
	public void updateSliders()
	{
		normalRange = (float)((SliderSetting)settings.get(0)).getValue();
	}
	
	@Override
	public void onDisable()
	{
		wurst.events.remove(UpdateListener.class, this);
		mc.thePlayer.stepHeight = 0.5F;
	}
}
