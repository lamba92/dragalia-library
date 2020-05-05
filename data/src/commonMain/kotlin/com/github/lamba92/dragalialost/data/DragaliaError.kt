package com.github.lamba92.dragalialost.data

abstract class DragaliaError : Throwable() {

    abstract override val cause: Throwable
    abstract override val message: String

    override fun toString() = "$message\n\tdue to $cause"
}
