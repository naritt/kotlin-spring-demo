package demo.controller

import com.google.cloud.bigtable.data.v2.BigtableDataClient
import com.google.cloud.bigtable.data.v2.BigtableDataSettings
import com.google.cloud.bigtable.data.v2.models.Row
import com.google.cloud.bigtable.data.v2.models.RowMutation
import demo.model.BigTableRequestBody
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cbt")
class BigTableController {
    @PostMapping("/insert")
    fun insert(@RequestBody requestbody: BigTableRequestBody): String {
        val projectId = "feisty-reporter-335214"
        val instanceId = "demo-instance-terraform"
        val tableId = "demo-table-terraform"

        val settings = BigtableDataSettings.newBuilder().setProjectId(projectId).setInstanceId(instanceId).build()
        val dataClient = BigtableDataClient.create(settings)

        val rowMutation: RowMutation = RowMutation.create(tableId, requestbody.id)
            .setCell("demo", "name", requestbody.name)
            .setCell("demo", "message", requestbody.message)
        dataClient.mutateRow(rowMutation)

        return "Success"
    }

    @GetMapping("/list")
    fun list(id: String): String {
        val projectId = "feisty-reporter-335214"
        val instanceId = "demo-instance-terraform"
        val tableId = "demo-table-terraform"

        val settings = BigtableDataSettings.newBuilder().setProjectId(projectId).setInstanceId(instanceId).build()
        val dataClient = BigtableDataClient.create(settings)

        val row: Row = dataClient.readRow(tableId, id)

        var response = ""
        for (cell in row.cells) {
            response += "Family: ${cell.family}, Qualifier: ${cell.qualifier.toStringUtf8()}, Value: ${cell.value.toStringUtf8()} ${System.lineSeparator()}"
        }

        return response
    }
}