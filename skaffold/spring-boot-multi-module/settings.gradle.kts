pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "spring-boot-multi-module"

include (
	"hello-world-1",
	"hello-world-2"
)
