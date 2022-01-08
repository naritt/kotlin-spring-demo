provider "google" {
  region = "asia-southeast1"
#  credentials = file("../terraform-credential.json")
  project = var.project_name
}

provider "google-beta" {
  region = "asia-southeast1"
#  credentials = file("../terraform-credential.json")
  project = var.project_name
}

terraform {
  backend "gcs" {
    bucket = "feisty-reporter-335214-terraform-state"
    prefix = "nprd"
#    credentials = "../terraform-credential.json"
  }
}