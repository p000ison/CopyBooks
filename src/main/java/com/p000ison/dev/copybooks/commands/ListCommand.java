/*
 * Copyright (C) 2012 p000ison
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License. To view a copy of
 * this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/ or send
 * a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco,
 * California, 94105, USA.
 * 
 */
package com.p000ison.dev.copybooks.commands;

import com.p000ison.dev.copybooks.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Max
 */
public class ListCommand extends GenericCommand {

    private final int MAX_BOOKS_PER_PAGE = 9;

    public ListCommand(CopyBooks plugin, String name)
    {
        super(plugin, name);
        setPermissions("cb.command.list");
        setUsages("/cb list - Lists all books");
        setArgumentRange(0, 1);
        setIdentifiers("list");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args)
    {
        int multiplier = 1;

        if (args.length == 1) {
            multiplier = Integer.parseInt(args[0]);
        }

        List<BasicBook> books = plugin.getStorageManager().retrieveBooks((multiplier - 1) * MAX_BOOKS_PER_PAGE, multiplier * MAX_BOOKS_PER_PAGE);
        Iterator<BasicBook> it = books.iterator();

        while (it.hasNext()) {
            BasicBook book = it.next();
            if (Book.hasPermission(book.getCreator(), sender)) {
                it.remove();
            }
        }


        if (books.isEmpty()) {
            sender.sendMessage("No books found!");
            return;
        }

        ChatBlock chatBlock = new ChatBlock();
        ChatBlock.sendBlank(sender);
        ChatBlock.saySingle(sender, "List");
        ChatBlock.sendBlank(sender);

        ChatBlock.sendBlank(sender);

        chatBlock.setFlexibility(true, false, false, false);
        chatBlock.setAlignment("l", "c", "c", "c");

        chatBlock.addRow(ChatColor.AQUA + "  " + "ID", "Title", "Author", "Creator");

        for (BasicBook book : books) {

            if (book != null) {
                String id = String.valueOf(book.getId());
                String title = book.getTitle();
                String author = book.getAuthor();
                String creator = book.getCreator();

                chatBlock.addRow(ChatColor.GRAY + "  " + id, title, author, creator);
            }
        }

        chatBlock.sendBlock(sender, MAX_BOOKS_PER_PAGE + 3);

    }
}
