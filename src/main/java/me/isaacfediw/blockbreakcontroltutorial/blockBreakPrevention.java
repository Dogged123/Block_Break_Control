package me.isaacfediw.blockbreakcontroltutorial;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

//Make sure your class implements Listener
public class blockBreakPrevention implements Listener {

    //Now create a new arrayList
    private ArrayList<Block> breakableBlocks = new ArrayList<>();

    //This first method runs whenever a player places a block and if they are not OP it adds it to the arrayList we just created
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        //Note: You do not have to have the following if statement if you do not want ops to bypass this method
        //You can also replace "isOp()" with (getGamemode.equalsIgnoreCase("creative"))
        if(e.getPlayer().isOp()){
            return;
        }
        //The return statement just exits the method and any blocks that an op places are NOT added to the list.
        //This will make it so that no one can break blocks placed by an op

        //This next line simply adds the block that was placed to the arrayList
        breakableBlocks.add(e.getBlock());
        //And the first method is done!
    }

    //The second method runs whenever a block is broken by a player (Technically just before it is broken)
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        //Make a new player
        Player p = e.getPlayer();
        //Check if they are not op. (If they are they will bypass the breaking restrictions)
        if (!p.isOp()){
            //This next if statement checks if the arrayList does NOT contain the block being broken
            //If this is the case it means that the block was not placed by a player during the game and should not be broken
            if (!breakableBlocks.contains(e.getBlock())){
                //Cancels the block break event
                e.setCancelled(true);
                //Now send them a message of your choice (This is optional)
                p.sendMessage(ChatColor.RED + "You can only break blocks placed by a player!");
            }
        }
        //And now we are done! All we need to do now is initialize this class in our main class and test it out!
    }
}
