import jenkins.model.*
def uname
def upasswd
def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        com.cloudbees.plugins.credentials.Credentials.class,
        Jenkins.instance,
        null,
        null
);

for (c in creds){
    if (c.id == "registry") {
        uname = c.getUsername()
        upasswd = c.getPassword().toString()
    }
}


def addr       = "https://ci-dms.ap.aws.griddynamics.net:5000/v2/dms-assistant/tags/list"
def authString = "${uname}:${upasswd}".getBytes().encodeBase64().toString()

def conn = addr.toURL().openConnection()
conn.setRequestProperty( "Authorization", "Basic ${authString}" )

def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(conn.content.text)


return result.tags