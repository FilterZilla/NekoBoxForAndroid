package io.nekohasekai.sagernet.ui.profile

import io.nekohasekai.sagernet.fmt.v2ray.VMessBean

class VMessSettingsActivity : StandardV2RaySettingsActivity() {

    override fun createEntity() = VMessBean()

}