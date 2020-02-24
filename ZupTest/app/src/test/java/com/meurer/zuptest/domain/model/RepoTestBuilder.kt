package com.meurer.zuptest.domain.model

import com.meurer.zuptest.model.Owner
import com.meurer.zuptest.model.Repo

class RepoTestBuilder {
    private var id: Int = 0
    private var name: String =""
    private var fullname: String? = ""
    private var description: String? = ""
    private var owner: Owner = Owner("","")

    fun build() = Repo(
        id, name, fullname, description, owner
    )

    fun withTestValues() : RepoTestBuilder {
        id = 1932083
        name = "HealthVault-Mobile-iOS-Library"
        fullname = "microsoft/HealthVault-Mobile-iOS-Library"
        description = "The HealthVault team has recently added the capability to write applications that will..."
        owner = Owner("microsoft", "https://api.github.com/users/microsoft")

        return this
    }

    fun withId(id:Int): RepoTestBuilder {
        this.id = id
        return this
    }

    fun withName(name:String) :RepoTestBuilder {
        this.name = name
        return this
    }

    fun withFullName(fullname: String) : RepoTestBuilder {
        this.fullname = fullname
        return this
    }

    fun withDescription(description:String):RepoTestBuilder{
        this.description = description
        return this
    }

    fun withOwner(login:String, url:String):RepoTestBuilder{
        this.owner.login = login
        this.owner.url = url
        return this
    }
}