variable "amis" {
    type = map
    description = "Imagens que serão utilizadas nas intâncias EC2"
    nullable = false
    default = {
        "us-east-1" = "ami-04505e74c0741db8d"
        "sp-east-1" = "ami-0056d4296b1120bc3"
    }
}

variable "cidr_blocks_access_ssh" {
    type = list(string)
    description = "IPs que terão acesso as instancias do EC2 via SSH"
    default = [ "177.172.55.155/32", "172.175.55.169/32"]  
}

variable "key_pair_name" {
    type = string
    description = "Nome da chave configurada para acesso via SSH as instâncias do EC2"
    default = "terraform-key"
}

variable "type_instace_t2" {
    type = string
    default = "t2.micro"
}