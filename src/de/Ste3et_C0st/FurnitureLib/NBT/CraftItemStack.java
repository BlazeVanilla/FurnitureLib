package de.Ste3et_C0st.FurnitureLib.NBT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.bukkit.inventory.ItemStack;

import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;

public class CraftItemStack {

	public NBTTagCompound getNBTTag(ItemStack is) throws Exception{
		try{
			Class<?> clazz_cis = Class.forName("org.bukkit.craftbukkit."+FurnitureLib.getInstance().getBukkitVersion()+".inventory.CraftItemStack");
			Object nms_item = clazz_cis.getMethod("asNMSCopy", org.bukkit.inventory.ItemStack.class).invoke(null, is);
			Object nms_nbt = Class.forName("net.minecraft.server."+FurnitureLib.getInstance().getBukkitVersion()+".NBTTagCompound").newInstance();
			nms_item.getClass().getMethod("save", nms_nbt.getClass()).invoke(nms_item, nms_nbt);
			Class<?> clazz_nbttools =  Class.forName("net.minecraft.server."+FurnitureLib.getInstance().getBukkitVersion()+".NBTCompressedStreamTools");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			clazz_nbttools.getMethod("a", nms_nbt.getClass(),OutputStream.class).invoke(null, nms_nbt,(OutputStream)os);
			byte[] out = os.toByteArray();
			NBTTagCompound c = NBTCompressedStreamTools.read(out,NBTReadLimiter.unlimited);
			return c;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ItemStack getItemStack(NBTTagCompound nbt){
		try{
			byte[] data = NBTCompressedStreamTools.toByte(nbt);
			Class<?> clazz_nms_NBTTagCompound = Class.forName("net.minecraft.server." + FurnitureLib.getInstance().getBukkitVersion() + ".NBTTagCompound");
			Class<?> clazz_nms_NBTCompressedStreamTools = Class.forName("net.minecraft.server." + FurnitureLib.getInstance().getBukkitVersion() + ".NBTCompressedStreamTools");
			Class<?> clazz_nms_ItemStack = Class.forName("net.minecraft.server." + FurnitureLib.getInstance().getBukkitVersion() + ".ItemStack");
			Class<?> clazz_nms_CraftItemStack = Class.forName("org.bukkit.craftbukkit." + FurnitureLib.getInstance().getBukkitVersion() + ".inventory.CraftItemStack");
			Object nbtTag = clazz_nms_NBTCompressedStreamTools.getMethod("a", InputStream.class).invoke(null,(InputStream) new ByteArrayInputStream(data));
			Object nms_item = clazz_nms_ItemStack.getMethod("a", clazz_nms_NBTTagCompound).invoke(null,nbtTag);
			org.bukkit.inventory.ItemStack item = (org.bukkit.inventory.ItemStack) clazz_nms_CraftItemStack.getMethod("asBukkitCopy", clazz_nms_ItemStack).invoke(null, nms_item);
			return item;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}