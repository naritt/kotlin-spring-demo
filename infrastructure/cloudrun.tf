resource "google_cloud_run_service" "demo" {
  name     = "${var.environment_name}-demo-terraform"
  location = "asia-southeast1"

  template {
    spec {
      containers {
        image = "asia-southeast1-docker.pkg.dev/feisty-reporter-335214/demo-docker-registry/kotlin-demo:latest"
        env {
          name = "ENVIRONMENT"
          value = var.environment_name
        }
        env {
          name = "SPRING_PROFILES_ACTIVE"
          value = var.environment_name
        }
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }
}

data "google_iam_policy" "noauth" {
  binding {
    role = "roles/run.invoker"
    members = [
      "allUsers",
    ]
  }
}

resource "google_cloud_run_service_iam_policy" "noauth" {
  location    = google_cloud_run_service.demo.location
  project     = google_cloud_run_service.demo.project
  service     = google_cloud_run_service.demo.name

  policy_data = data.google_iam_policy.noauth.policy_data
}