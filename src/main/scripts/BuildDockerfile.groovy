String baseDir = System.getProperty("user.dir")

def template = new File("\\src\\main\\docker\\DockerfileTemplate").text

def dockerFileText = new groovy.text.SimpleTemplateEngine().createTemplate(template)
        .make([fileName: project.build.finalName])

new File(baseDir + "\\target\\dockerfile").mkdirs()

File dockerFile = new File(baseDir + "\\target\\dockerfile\\Dockerfile")

dockerFile.withWriter("UTF-8") { writer ->
    writer.write(dockerFileText)
}
