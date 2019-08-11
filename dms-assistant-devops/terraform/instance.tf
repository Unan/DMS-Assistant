provider "aws" {
  region = "us-west-2"
}

variable "BUILD_NUMBER"{}
variable "NAME_INSTANCE"{}

resource "aws_instance" "terraform-ec2" {
  ami = "ami-01ed306a12b7d1c96"
  instance_type = "t2.micro"
  key_name = "dms-keys"
  vpc_security_group_ids = ["sg-0911a0347585e1061"]
  tags{
     Name = "dms-assistant-qa-${var.BUILD_NUMBER}"
     }
    root_block_device {
       delete_on_termination = true
     }
}

resource "aws_route53_record" "dms" {
  zone_id = "Z2NJXE7Z42KP34"
  name    = "${var.NAME_INSTANCE}"
  type    = "A"
  ttl     = "300"
  records = ["${aws_instance.terraform-ec2.public_ip}"]
}



