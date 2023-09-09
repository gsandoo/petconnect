package com.haneum.petconnect.contracts
import com.haneum.petconnect.data.UserAccount

interface RegisterContract {
    interface View {
        fun goLogin()
        fun makeFailureText(reason: String)
    }

    interface Presenter {
        fun register(email: String, pw: String, name: String, phone: String)
        fun updateData(user: UserAccount)
    }
}