// 
// Decompiled by Procyon v0.5.36
// 

package io.github.mertout.api.events;

import org.jetbrains.annotations.NotNull;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import io.github.mertout.core.data.DataHandler;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class ClaimDeleteEvent extends Event implements Cancellable
{
    public DataHandler data;
    public Player p;
    public static final HandlerList handlers;
    public boolean cancelled;
    
    public ClaimDeleteEvent(@NotNull final DataHandler x, final Player y) {
        this.data = x;
        this.p = y;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean arg0) {
        this.cancelled = arg0;
    }
    
    public HandlerList getHandlers() {
        return ClaimDeleteEvent.handlers;
    }
    
    public DataHandler getData() {
        return this.data;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    static {
        handlers = new HandlerList();
    }
}
