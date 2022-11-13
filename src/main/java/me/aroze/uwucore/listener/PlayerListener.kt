package me.aroze.uwucore.listener

import me.aroze.uwucore.santa.delay
import me.aroze.uwucore.util.isFlagged
import org.bukkit.Bukkit
import org.bukkit.EntityEffect
import org.bukkit.entity.Cat
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

object PlayerListener : Listener {

    @EventHandler
    fun onClick(e: PlayerInteractEvent) {
        val item = e.player.inventory.itemInMainHand ?: return
        if (e.action == Action.PHYSICAL) return
        if (e.hand == EquipmentSlot.OFF_HAND) return

        if (!item.isFlagged("kittyAbuser")) return

        val cat = e.player.world.spawnEntity(e.player.eyeLocation, EntityType.CAT) as Cat
        cat.velocity = e.player.eyeLocation.direction.multiply(2.5)
        cat.isInvulnerable = true

        delay({
            cat.playEffect(EntityEffect.ENTITY_POOF)
            cat.remove()
        }, 100)
    }

}