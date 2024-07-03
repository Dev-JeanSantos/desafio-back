package com.academy.fourtk.contract_services.repositories.mongo.adapter

import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import java.util.UUID.randomUUID

fun entityToDocument(
    entity: ContractEntity
) = ContractDocument(
    _id = randomUUID().toString(),
    personId = entity.personId,
    productId = entity.productId,
    status = entity.status,
    integrationServiceAPendent = entity.integrationServiceAPendent,
    integrationServiceBPendent = entity.integrationServiceBPendent,
    createdAt = entity.createdAt,
    updatedAt = entity.updatedAt,
    canceledAt = entity.updatedAt
)