resource "aws_s3_bucket" "dev3tf" {
  bucket = "bucket-dev3tf"
  acl    = "private"

  tags = {
    Name        = "bucket-dev3tf"
    Environment = "Dev"
  }
}