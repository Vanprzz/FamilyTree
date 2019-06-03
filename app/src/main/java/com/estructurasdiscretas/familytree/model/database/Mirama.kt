package com.estructurasdiscretas.familytree.model.database

import java.util.*

class Mirama {

    internal interface Tables {
        companion object {
            const val DB_VERSION = 1
            const val DB_NAME = "mirama.db"
            const val TABLE_PERSON = "person"
            const val TABLE_COUPLE = "couple"
            const val TABLE_SON = "son"
        }
    }

    internal interface PersonHeaders {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val DADLASTNAME = "dadlastname"
            const val MOMLASTNAME = "momlastname"
            const val LIFE = "life"
            const val GENDER = "gender"
            const val BD = "birthday"
            const val IMG = "img"
        }
    }

    internal interface CoupleHeaders {
        companion object {
            const val ID = "id"
            const val MAN = "idman"
            const val WOMAN = "idwoman"
            const val ID_COUPLE_PARENT_MAN = "id_cousin_parent_man"
            const val ID_COUPLE_PARENT_WOMAN = "id_cousin_parent_woman"
        }
    }

    internal interface SonHeaders {
        companion object {
            const val ID = "id"
            const val ID_COUPLE = "idcouple"
            const val ID_COUPLE_SON = "idcoupleSon"
        }
    }

    class IdPerson : PersonHeaders {
        companion object {
            fun generatePersonID(): String {
                return "P-" + UUID.randomUUID().toString()
            }
        }
    }

    class IdCouple : CoupleHeaders {
        companion object {
            fun generateCoupleID(): String {
                return "C-" + UUID.randomUUID().toString()
            }
        }
    }

    class IdSon : SonHeaders {
        companion object {
            fun generateSonID(): String {
                return "CS-" + UUID.randomUUID().toString()
            }
        }
    }
}