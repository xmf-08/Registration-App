package xmf.developer.registration.DB

import xmf.developer.registration.models.MyShablon

interface MyDbHelperInterface {
    fun addShablon(myShablon: MyShablon)
    fun getAllShablons(): ArrayList<MyShablon>
}