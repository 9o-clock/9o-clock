rootProject.name = "9o-clock-server"

include(":applications:9o-clock-external-api")
include(":applications:9o-clock-internal-api")

include(":components:quiz:app-quiz")
include(":components:quiz:domain-quiz")
include(":components:quiz:interface-quiz")

include(":components:user:app-user")
include(":components:user:domain-user")
include(":components:user:interface-user")

include(":components:@shard")

include(":infrastructures-data:data-jpa")
