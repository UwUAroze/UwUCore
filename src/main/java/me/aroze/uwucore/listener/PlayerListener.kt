package me.aroze.uwucore.listener

import me.aroze.uwucore.UwUCore
import me.aroze.uwucore.command.KittyCannonCommand
import me.aroze.uwucore.santa.async
import me.aroze.uwucore.santa.delay
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
        if (e.item == null) return
        if (e.action == Action.PHYSICAL) return
        if (e.hand == EquipmentSlot.OFF_HAND) return

        val heldItem = e.item!!.itemMeta!!.displayName
        val kittyGun: String = KittyCannonCommand.kittyCannon.itemMeta!!.displayName

        if (heldItem != kittyGun) return

        val cat = e.player.world.spawnEntity(e.player.eyeLocation, EntityType.CAT) as Cat
        cat.velocity = e.player.eyeLocation.direction.multiply(2.5)
        cat.isInvulnerable = true

        delay({
            cat.playEffect(EntityEffect.ENTITY_POOF)
            cat.remove()
        }, 100)
    }

}