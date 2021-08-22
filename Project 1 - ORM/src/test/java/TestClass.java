import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.PrimaryKey;
import com.revature.orm.annotations.Table;

@Table(tableName = "test")
public class TestClass
{
    @PrimaryKey(primaryKeyName = "primary key")
    private int primaryKey;

    @Column(columnName = "column")
    private String column;

    public TestClass()
    {
        primaryKey = 1;
        column = "whatever";
    }
}
