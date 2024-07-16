package com.academy.fourtk.product_services.repositories.mongo.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {
    @Value("\${spring.data.mongodb.database}")
    private lateinit var databaseName: String

    @Value("\${spring.data.mongodb.host}")
    private lateinit var host: String

    @Value("\${spring.data.mongodb.port}")
    private lateinit var port: String

    @Value("\${spring.data.mongodb.username}")
    private lateinit var username: String

    @Value("\${spring.data.mongodb.password}")
    private lateinit var password: String

    override fun mongoClient(): MongoClient {
        val connectionString = "mongodb://$username:$password@$host:$port/$databaseName"
        return MongoClients.create(connectionString)
    }

    override fun getDatabaseName(): String {
        return databaseName
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val databaseFactory = SimpleMongoClientDatabaseFactory(mongoClient(), databaseName)
        val converter = mappingMongoConverter(databaseFactory, MongoMappingContext())
        return MongoTemplate(databaseFactory, converter)
    }

    @Bean
    fun mappingMongoConverter(
        databaseFactory: SimpleMongoClientDatabaseFactory,
        mappingContext: MongoMappingContext
    ): MappingMongoConverter {
        val dbRefResolver = DefaultDbRefResolver(databaseFactory)
        val converter = MappingMongoConverter(dbRefResolver, mappingContext)

        // Remove _class from being saved
        converter.setTypeMapper(DefaultMongoTypeMapper(null))

        return converter
    }
}