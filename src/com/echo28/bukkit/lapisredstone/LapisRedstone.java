package com.echo28.bukkit.lapisredstone;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * LapisRedstone for Bukkit
 * 
 * @author Nodren
 */
public class LapisRedstone extends JavaPlugin
{
	private final LapisRedstoneBlockListener blockListener = new LapisRedstoneBlockListener(this);
	private final Logger log = Logger.getLogger("Minecraft");
	private int MIN;
	private int MAX;
	private int CHANCE;
	private int EXTRA_LAPIS_MIN;
	private int EXTRA_LAPIS_MAX;
	private int EXTRA_LAPIS_CHANCE;

	public LapisRedstone(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader)
	{
		super(pluginLoader, instance, desc, folder, plugin, cLoader);
		folder.mkdirs();

		File yml = new File(getDataFolder(), "config.yml");
		if (!yml.exists())
		{
			try
			{
				yml.createNewFile();
			}
			catch (IOException ex)
			{
			}
		}
		CHANCE = getConfiguration().getInt("chance", 100); //chance of dropping anything
		MIN = getConfiguration().getInt("min", 2);
		MAX = getConfiguration().getInt("max", 4);
		EXTRA_LAPIS_MIN = getConfiguration().getInt("extra_lapis_min", 3);
		EXTRA_LAPIS_MAX = getConfiguration().getInt("extra_lapis_max", 7);
		EXTRA_LAPIS_CHANCE = getConfiguration().getInt("extra_lapis_chance", 100);
	}

	public void onDisable()
	{
		log.info(getDescription().getName() + " " + getDescription().getVersion() + " unloaded.");
	}

	public void onEnable()
	{
		log.info(getDescription().getName() + " " + getDescription().getVersion() + " loaded.");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGED, blockListener, Priority.Monitor, this);
	}

	public int random()
	{
		if (CHANCE < 100){
			int roll = (int)(Math.random()*100);
			if (roll > CHANCE){
				return 0; //failed the roll, don't generate any.
			}
		}
		
		
		return MIN + (int) (Math.random() * ((MAX - MIN) + 1));
	}
	
	public int random_lapis(){
		if(EXTRA_LAPIS_CHANCE < 100){
			int roll = (int)(Math.random()*100);
			if (roll > EXTRA_LAPIS_CHANCE){
				return 0;
			}
		}
		
		return EXTRA_LAPIS_MIN + (int) (Math.random()* ((EXTRA_LAPIS_MAX - EXTRA_LAPIS_MIN + 1)));
		
	}

}
