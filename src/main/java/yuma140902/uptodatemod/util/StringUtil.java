package yuma140902.uptodatemod.util;

import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.yumalib.api.util.NameProvider;

public class StringUtil {
	private StringUtil() {}
	
	public static String surfix(String sep, String value) {
		return value.isEmpty() ? "" : sep + value;
	}
	
	public static String prefix(String value, String sep) {
		return value.isEmpty() ? "" : value + sep;
	}
	
	public static final NameProvider name = new NameProvider(ModUpToDateMod.MOD_TEXTURE_DOMAIN, ModUpToDateMod.MOD_UNLOCALIZED_ENTRY_DOMAIN); 
	
}
