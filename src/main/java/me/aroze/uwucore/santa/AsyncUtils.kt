package me.aroze.uwucore.santa

import me.aroze.uwucore.UwUCore
import org.bukkit.scheduler.BukkitTask
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer


fun async(code: Consumer<BukkitTask>) {
    UwUCore.getInstance().server.scheduler.runTaskAsynchronously(UwUCore.getInstance(), code)
}

fun sync(code: Consumer<BukkitTask>) {
    UwUCore.getInstance().server.scheduler.runTask(UwUCore.getInstance(), code)
}

fun delay(code: Consumer<BukkitTask>, ticks: Int) {
    UwUCore.getInstance().server.scheduler.runTaskLater(UwUCore.getInstance(), code, ticks.toLong())
}

fun timer(code: Consumer<BukkitTask>, times: Int, delay: Int) {
    val ran = AtomicInteger()
    UwUCore.getInstance().server.scheduler.runTaskTimer(UwUCore.getInstance(), { it: BukkitTask ->
        code.accept(it)
        ran.getAndIncrement()
        if (ran.get() == times) it.cancel()
    }, 0, delay.toLong())
}