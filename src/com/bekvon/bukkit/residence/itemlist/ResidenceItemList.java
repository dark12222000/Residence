/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekvon.bukkit.residence.itemlist;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.permissions.PermissionGroup;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 *
 * @author Administrator
 */
public class ResidenceItemList extends ItemList {
    ClaimedResidence res;

    public ResidenceItemList(ClaimedResidence parent, ListType type)
    {
        super(type);
        res = parent;
    }

    private ResidenceItemList()
    {
        
    }

    public void playerListChange(Player player, Material mat, boolean resadmin) {
        PermissionGroup group = Residence.getPermissionManager().getGroup(player);
        if(resadmin || (res.getPermissions().hasResidencePermission(player, true) && group.itemListAccess()))
        {
            if(super.toggle(mat))
                player.sendMessage("§e" + Residence.getLanguage().getPhrase("ListMaterialAdd","§a" + mat + "§e.§a" + type.toString().toLowerCase() + "§e"));
            else
                player.sendMessage("§e" + Residence.getLanguage().getPhrase("ListMaterialRemove","§a" + mat + "§e.§a" + type.toString().toLowerCase() + "§e"));
        }
        else
        {
            player.sendMessage("§c"+Residence.getLanguage().getPhrase("NoPermission"));
        }
    }

    public static ResidenceItemList load(ClaimedResidence parent, Map<String,Object> map)
    {
        ResidenceItemList newlist = new ResidenceItemList();
        newlist.res = parent;
        return (ResidenceItemList) ItemList.load(map, newlist);
    }
}
