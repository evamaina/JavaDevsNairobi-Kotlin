package com.example.javadevsnairobi.models

class User {

    var image: Int = 0
    lateinit var name: String
    var repositories: Int = 0

    constructor() {}

    constructor(image: Int, name: String, repositories: Int) {
        this.image = image
        this.name = name
        this.repositories = repositories
    }
}
