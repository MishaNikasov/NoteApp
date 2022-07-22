package com.app.data.mapper

interface Mapper<Model, Entity> {

    fun mapModel(model: Model): Entity

    fun mapEntity(entity: Entity): Model

    fun mapModelList(modelList: List<Model>): List<Entity> {
        return modelList.map { e -> mapModel(e) }
    }

    fun mapEntityList(entityList: List<Entity>): List<Model> {
        return entityList.map { e -> mapEntity(e) }
    }

}