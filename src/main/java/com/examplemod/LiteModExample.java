package com.examplemod;

import com.mumfrey.liteloader.PreRenderListener;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import java.io.File;

@ExposableOptions(strategy = ConfigStrategy.Versioned, filename="examplemod.json")
public class LiteModExample implements Tickable, PreRenderListener
{
	private static KeyBinding toggleJumpKeybinding = new KeyBinding("key.jump.toggle", Keyboard.KEY_P, "key.categories.litemods");

	public LiteModExample()
	{
	}

	@Override
	public String getName()
	{
		return "Toggle Auto Jump";
	}

	@Override
	public String getVersion()
	{
		return "1.0";
	}

	@Override
	public void init(File configPath)
	{
		LiteLoader.getInput().registerKeyBinding(LiteModExample.toggleJumpKeybinding);
	}

	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}
	
	@Override
	public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock)
	{
		if (inGame && minecraft.currentScreen == null)
		{
			if (LiteModExample.toggleJumpKeybinding.isPressed())
			{
				Minecraft.getMinecraft().gameSettings.autoJump = !Minecraft.getMinecraft().gameSettings.autoJump;

				ITextComponent message = new TextComponentString("");
				ITextComponent prefix = new TextComponentString("[ToggleJump] ");
				prefix.setStyle(new Style().setColor(TextFormatting.GOLD));

				ITextComponent text;

				if (Minecraft.getMinecraft().gameSettings.autoJump)
				{
					text = new TextComponentString("Auto jump is now enabled");
				}
				else
				{
					text = new TextComponentString("Auto jump is now disabled");
				}

				message.appendSibling(prefix);
				message.appendSibling(text);
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(message);
			}
		}
	}

	@Override
	public void onRenderWorld(float partialTicks)
	{
	}

	@Override
	public void onSetupCameraTransform(float partialTicks, int pass, long timeSlice)
	{
	}

	@Override
	public void onRenderSky(float partialTicks, int pass)
	{
	}

	@Override
	public void onRenderClouds(float partialTicks, int pass, RenderGlobal renderGlobal)
	{
	}

	@Override
	public void onRenderTerrain(float partialTicks, int pass)
	{
	}
}
