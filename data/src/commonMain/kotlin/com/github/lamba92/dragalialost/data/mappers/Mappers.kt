package com.github.lamba92.dragalialost.data.mappers

interface SingleFromRemoteMapper<SingleRemote, Entity> {
    fun fromRemoteSingle(remote: SingleRemote): Entity

    operator fun invoke(remote: SingleRemote) =
        fromRemoteSingle(remote)
}

interface MultipleFromRemoteMapper<MultipleRemote, SingleRemote, Entity> :
    SingleFromRemoteMapper<SingleRemote, Entity> {
    fun fromRemoteMultiple(remote: MultipleRemote): List<Entity>
}

interface SingleToRemoteMapper<Remote, Entity> {
    fun toRemote(entity: Entity): Remote
}

interface MultipleToRemoteMapper<MultipleRemote, SingleRemote, Entity> : SingleToRemoteMapper<SingleRemote, Entity> {
    fun toRemoteMultiple(remote: MultipleRemote): List<Entity>
}
