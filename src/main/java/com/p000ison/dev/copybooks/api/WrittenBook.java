package com.p000ison.dev.copybooks.api;


/**
 * Represents a WrittenBook
 */
public interface WrittenBook {

    public String getTitle();

    public String getAuthor();

    public java.util.List<String> getPages();

    public void setTitle(String title);

    public void setAuthor(String author);

    public void setPages(java.util.List<String> pages) throws InvalidBookException;

    public boolean unsign();

    public org.bukkit.inventory.ItemStack toItemStack(int amount) throws InvalidBookException;
}
