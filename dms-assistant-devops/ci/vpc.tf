
resource "aws_security_group" "instance" {
  name = "DMS-env-qa"

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["89.22.1.0/24"]
  }

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["54.202.187.125/32"]
  }
  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["172.31.16.0/20"]
  }
  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["172.31.32.0/20"]
  }
  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["172.31.0.0/20"]
  }

  ingress {
      from_port = 22
      to_port = 22
      protocol = "tcp"
      cidr_blocks = ["188.234.130.105/32"]
    }
  ingress {
      from_port = 22
      to_port = 22
      protocol = "tcp"
      cidr_blocks = ["178.45.248.250/32"]
    }
  ingress {
        from_port = 22
        to_port = 22
        protocol = "tcp"
        cidr_blocks =  ["4.28.11.34/32"]
      }
  ingress {
         from_port = 22
         to_port = 22
         protocol = "tcp"
         cidr_blocks = ["87.245.249.103/32"]
       }
  ingress {
          from_port = 22
          to_port = 22
          protocol = "tcp"
          cidr_blocks = ["83.142.185.126/32"]
          }

  ingress {
        from_port = 80
        to_port = 80
        protocol = "tcp"
        cidr_blocks = ["89.22.1.0/24"]
      }
  ingress {
          from_port = 80
          to_port = 80
          protocol = "tcp"
          cidr_blocks = ["188.234.130.105/32"]
        }
  ingress {
          from_port = 80
          to_port = 80
          protocol = "tcp"
          cidr_blocks = ["178.45.248.250/32"]
        }
  ingress {
           from_port = 80
           to_port = 80
           protocol = "tcp"
           cidr_blocks =  ["4.28.11.34/32"]
          }
  ingress {
           from_port = 80
           to_port = 80
           protocol = "tcp"
           cidr_blocks = ["87.245.249.103/32"]
           }
  ingress {
          from_port = 80
          to_port = 80
          protocol = "tcp"
          cidr_blocks = ["83.142.185.126/32"]
           }
  ingress {
      from_port = 80
      to_port = 80
      protocol = "tcp"
      cidr_blocks = ["54.202.187.125/32"]
    }
    ingress {
      from_port = 80
      to_port = 80
      protocol = "tcp"
      cidr_blocks = ["172.31.16.0/20"]
    }
    ingress {
      from_port = 80
      to_port = 80
      protocol = "tcp"
      cidr_blocks = ["172.31.32.0/20"]
    }
    ingress {
      from_port = 80
      to_port = 80
      protocol = "tcp"
      cidr_blocks = ["172.31.0.0/20"]
    }



  ingress {
       from_port = 443
       to_port = 443
       protocol = "tcp"
       cidr_blocks = ["89.22.1.0/24"]
    }
  ingress {
        from_port = 443
        to_port = 443
        protocol = "tcp"
        cidr_blocks = ["188.234.130.105/32"]
     }
  ingress {
        from_port = 443
        to_port = 443
        protocol = "tcp"
        cidr_blocks = ["178.45.248.250/32"]
     }
  ingress {
        from_port = 443
        to_port = 443
        protocol = "tcp"
        cidr_blocks = ["4.28.11.34/32"]
     }
  ingress {
        from_port = 443
        to_port = 443
        protocol = "tcp"
        cidr_blocks = ["87.245.249.103/32"]
     }
 ingress {
         from_port = 443
         to_port = 443
         protocol = "tcp"
         cidr_blocks = ["83.142.185.126/32"]
      }

 egress {
     from_port = 0
     to_port = 65535
     protocol = "tcp"
     cidr_blocks = ["0.0.0.0/0"]
   }
}