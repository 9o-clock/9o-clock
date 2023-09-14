rootProject.name = "9o-clock-server"

include(":applications:9o-clock-external-api")
include(":applications:9o-clock-internal-api")

include(":components:component-quiz")
include(":components:component-user")

include(":domains:domain-quiz")
include(":domains:domain-user")
include(":domains:@shard")

include(":infrastructures-data:data-jpa")

include(":interfaces:user")
