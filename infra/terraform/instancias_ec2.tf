resource "aws_instance" "devtf" {
  ami = var.amis["us-east-1"]
  count = 2
  instance_type = var.type_instace_t2
  key_name = var.key_pair_name
  tags = {
    Name = "dev_${count.index}"
  }
  //security_groups = ["ssh-access-tf"] esse e o de baixo tem o mesmo resultado
  vpc_security_group_ids = ["${aws_security_group.ssh-access-tf.id}"]
}

resource "aws_instance" "dev3tf" {
  ami = var.amis["us-east-1"]
  instance_type = var.type_instace_t2
  key_name = var.key_pair_name
  tags = {
    Name = "dev3tf"
  }
  vpc_security_group_ids = ["${aws_security_group.ssh-access-tf.id}"]
  depends_on = [aws_s3_bucket.dev3tf]
}

resource "aws_instance" "dev4tf" {
  ami = var.amis["us-east-1"]
  instance_type = var.type_instace_t2
  key_name = var.key_pair_name
  tags = {
    Name = "dev4tf"
  }
  security_groups = ["ssh-access-tf"]
}

resource "aws_instance" "dev5tf" {
  ami = var.amis["us-east-1"]
  instance_type = var.type_instace_t2
  key_name = var.key_pair_name
  tags = {
    Name = "dev5tf"
  }
  security_groups = ["ssh-access-tf"]
}

resource "aws_instance" "dev1tf-sp" {
  provider = aws.sa-east-1
  ami = var.amis["sp-east-1"]
  instance_type = var.type_instace_t2
  key_name = var.key_pair_name
  tags = {
    Name = "dev1tf-sp"
  }
  security_groups = ["ssh-access-tf-sp"]
  depends_on = [aws_dynamodb_table.dynamodb-table-sp]
}