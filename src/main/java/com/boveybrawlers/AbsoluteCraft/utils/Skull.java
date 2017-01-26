package com.boveybrawlers.AbsoluteCraft.utils;

import java.util.Base64;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

public class Skull {

    public static ItemStack makePlayer(String username) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(username);

        return item;
    }

    public static ItemStack makeCustom(String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"http://textures.minecraft.net/texture/%s\"}}}", url).getBytes());

        PropertyMap propertyMap = profile.getProperties();
        if(propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        propertyMap.put("textures", new Property("textures", new String(encodedData)));

        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta skullMeta = item.getItemMeta();
        Class<?> skullMetaClass = skullMeta.getClass();
        Reflections.getField(skullMetaClass, "profile", GameProfile.class).set(skullMeta, profile);
        item.setItemMeta(skullMeta);

        return item;
    }

}
