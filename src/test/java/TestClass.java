import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.PrimaryKey;
import com.revature.orm.annotations.Table;

@Table(tableName = "test")
public class TestClass
{
    @PrimaryKey(primaryKeyName = "primary key")
    private int primaryKey;

    @Column(columnName = "column", parameterNumber = 0)
    private String column;

    @MetamodelConstructor
    public TestClass(int primaryKey, String column)
    {
        this.primaryKey = primaryKey;
        this.column = column;
    }
}
