package com.github.lamba92.dragalia.di

import org.kodein.di.Kodein
import org.kodein.di.Kodein.OverridingException

interface KodeinModuleProvider {
    fun provideModule(): Kodein.Builder.() -> Unit
}

fun KodeinModuleProvider.getModule() =
    Kodein.Module(name = this::class.simpleName!!, init = provideModule())

/**
 * Imports all bindings defined in the given [KodeinModuleProvider] into this builder's definition.
 *
 * Note that modules are *definitions*, they will re-declare their bindings in each kodein instance you use.
 *
 * @param moduleProvider The module provider object to import.
 * @param allowOverride Whether this module is allowed to override existing bindings.
 *                      If it is not, overrides (even explicit) will throw an [OverridingException].
 * @throws OverridingException If this module overrides an existing binding and is not allowed to
 *                             OR [allowOverride] is true while YOU don't have the permission to override.
 */
fun Kodein.MainBuilder.import(
    moduleProvider: KodeinModuleProvider,
    allowOverride: Boolean = false
) = import(moduleProvider.getModule(), allowOverride)


/**
 * Imports all bindings defined in the given [Kodein.Module] into this builder's definition.
 *
 * Note that modules are *definitions*, they will re-declare their bindings in each kodein instance you use.
 *
 * @param module The module object to import.
 * @param allowOverride Whether this module is allowed to override existing bindings.
 *                      If it is not, overrides (even explicit) will throw an [OverridingException].
 * @throws OverridingException If this module overrides an existing binding and is not allowed to
 *                             OR [allowOverride] is true while YOU don't have the permission to override.
 */
fun Kodein.Builder.import(module: KodeinModuleProvider, allowOverride: Boolean = false) =
    import(module.getModule(), allowOverride)
