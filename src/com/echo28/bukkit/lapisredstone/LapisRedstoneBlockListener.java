package com.echo28.bukkit.lapisredstone;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;


public class LapisRedstoneBlockListener extends BlockListener
{
	private LapisRedstone plugin;

	public LapisRedstoneBlockListener(LapisRedstone instance)
	{
		plugin = instance;
	}

	@Override
	public void onBlockDamage(BlockDamageEvent event)
	{
		if (event.isCancelled()) { return; }
		if (event.getDamageLevel() == org.bukkit.block.BlockDamageLevel.BROKEN)
		{
			if ((event.getBlock().getType() == Material.REDSTONE_ORE) || (event.getBlock().getType() == Material.GLOWING_REDSTONE_ORE))
			{
				Location locy = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ(), 0, 0);
				// Material.INK_SACK
				int amount = plugin.random();
				for (int i = 0; i < amount; i++)
				{
					event.getBlock().getWorld().dropItemNaturally(locy, new ItemStack(Material.INK_SACK, 1, (byte) 4));
				}
			}
		}
	}
}
