import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.awscredentials.AWSCredentialsImpl
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.route53.AmazonRoute53
import com.amazonaws.services.route53.AmazonRoute53Client;
import com.amazonaws.services.route53.model.GetHostedZoneRequest;
import com.amazonaws.services.route53.model.HostedZone;
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest;
import com.amazonaws.services.route53.model.ResourceRecordSet

def credentialsStore = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0]?.getStore()
domain = new Domain(null, null, Collections.<DomainSpecification>emptyList())

def AWS_ACCESS_KEY_ID,AWS_SECRET_KEY
credentialsStore?.getCredentials(domain).each{

    if(it instanceof AWSCredentialsImpl){
        AWS_ACCESS_KEY_ID= it.accessKey
        AWS_SECRET_KEY=it.secretKey?.getPlainText()}
}

def ROUT53_HOSTED_ZONE_ID = "Z2NJXE7Z42KP34"
BasicAWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY)
AmazonRoute53 route53 = new AmazonRoute53Client(credentials)
HostedZone hostedZone = route53.getHostedZone(new GetHostedZoneRequest(ROUT53_HOSTED_ZONE_ID)).getHostedZone()
ListResourceRecordSetsRequest listResourceRecordSetsRequest = new ListResourceRecordSetsRequest()
        .withHostedZoneId(hostedZone.getId())

List<ResourceRecordSet> mas = route53.listResourceRecordSets(listResourceRecordSetsRequest).resourceRecordSets

def AWS_env = ["dms-qa01", "dms-qa02", "dms-qa03", "dms-qa04"]
ArrayList<String> aws_result_env = new ArrayList<>()
for ( String name : AWS_env) {
    boolean f=false
    for (ResourceRecordSet record : mas) {
        if (record.getName().contains(name)) {
            f=true
        }
    }
    if (!f)
        aws_result_env.add(name)
}

return aws_result_env.toString()
