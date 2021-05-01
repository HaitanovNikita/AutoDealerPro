package autodealer.com.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static autodealer.com.logic.utils.SqlConstants.*;

/**
 * @author nhaitanov
 */
@Entity
@Table(name = "IntegrationData")
@NamedNativeQuery(query = "Select * from IntegrationData as i where i.answerId = :aId and i.section = :section", name = nameQueryForFindingByAnswerIdAndSection)
@NamedNativeQuery(query = "Select * from IntegrationData as i where i.text = :text", name = nameQueryForFindingByText)
@NamedNativeQuery(query = "Select * from IntegrationData as i where i.section = :section", name = nameQueryForFindingBySection)
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class IntegrationData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "aId")
    private long answerId;

    @Column(name = "text")
    private String text;

    @Column(name = "section")
    private long section;
}
