package io.github.karadkar.popularmovies.data.mapper

interface EntityMapper<Entity, Domain> {
    fun mapFromEntiry(entity: Entity): Domain
    fun mapToEntity(domain: Domain): Entity
}