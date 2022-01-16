resource "google_bigtable_instance" "instance" {
  name = "demo-instance-terraform"
  deletion_protection = false

  cluster {
    cluster_id   = "demo-cluster-terraform-s1a"
    zone         = "asia-southeast1-a"
    num_nodes    = 2
    storage_type = "HDD"
  }

  # for ha multiple zone same region
#  cluster {
#    cluster_id   = "demo-cluster-terraform-s1b"
#    zone         = "asia-southeast1-b"
#    num_nodes    = 2
#    storage_type = "HDD"
#  }

  # for near-real-time backup to this cluster same region
#  cluster {
#    cluster_id = "demo-cluster-terraform-s1c"
#    zone         = "asia-southeast1-c"
#    num_nodes    = 1
#    storage_type = "HDD"
#  }

#  lifecycle {
#    prevent_destroy = true
#  }
}

#resource "google_bigtable_app_profile" "live_traffic" {
#  app_profile_id = "live-traffic"
#  instance = google_bigtable_instance.instance.name
#  ignore_warnings = true
#  multi_cluster_routing_use_any = true
#}

resource "google_bigtable_table" "table" {
  name          = "demo-table-terraform"
  instance_name = google_bigtable_instance.instance.name
  column_family {
    family = "demo"
  }

#  lifecycle {
#    prevent_destroy = true
#  }
}