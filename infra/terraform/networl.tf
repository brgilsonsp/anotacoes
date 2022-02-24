resource "aws_security_group" "ssh-access-tf" {
  name        = "ssh-access-tf"
  description = "Allow SSH to TF"

  ingress {
    description      = "ssh-access-tf"
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = var.cidr_blocks_access_ssh
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "SSH to TF"
  }
}

resource "aws_security_group" "ssh-access-tf-sp" {
  provider = aws.sa-east-1
  name        = "ssh-access-tf-sp"
  description = "Allow SSH to TF"

  ingress {
    description      = "ssh-access-tf-sp"
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = var.cidr_blocks_access_ssh
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "SSH to TF"
  }
}