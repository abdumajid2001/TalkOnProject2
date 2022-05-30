package com.talkon.talkon.repositories.chat;

import com.talkon.talkon.entities.conversation.chat.Chat;
import com.talkon.talkon.projections.chat.ChatProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends AbstractRepository<Chat, String> {
    @Query(nativeQuery = true, value = "select (select case when :currentId = u_mentor.id then u_mentee.first_name else u_mentor.first_name end) as \"firstName\",\n" +
            "                   (select case when :currentId = u_mentor.id then u_mentee.last_name else u_mentor.last_name end)   as \"lastName\",\n" +
            "                   c.id                                                                                       as \"chatId\",\n" +
            "                   (select case when :currentId = u_mentor.id then u_mentee.id else u_mentor.id end)          as \"userId\",\n" +
            "                   (select case when :currentId = u_mentor.id then (case when u_mentee.is_online then null else u_mentee.last_seen end)\n" +
            "                   else (case when u_mentor.is_online then null else u_mentor.last_seen end ) end)   as \"lastSeen\",\n" +
            "                   (select case when :currentId = u_mentor.id then u_mentee.is_online else u_mentor.is_online end)   as \"isOnline\",\n" +
            "                   (select count(id)\n" +
            "                    from conversation.messages m\n" +
            "                    where m.chat_id = c.id\n" +
            "                      and not m.is_read)                                                                      as \"newMessageCount\",\n" +
            "                   (select case when :currentId = u_mentor.id then u_mentee.photo_path else u_mentor.photo_path end) as \"photoPath\"\n" +
            "            from conversation.chats c\n" +
            "                     join users.mentees on c.mentee_id = mentees.id\n" +
            "                     join users.mentors on c.mentor_id = mentors.id\n" +
            "                     join users.users u_mentee on mentees.user_id = u_mentee.id\n" +
            "                     join users.users u_mentor on mentors.user_id = u_mentor.id\n" +
            "            where u_mentee.id = :currentId\n" +
            "               or u_mentor.id = :currentId\n" +
            "               order by \"newMessageCount\" desc")
    List<ChatProjection> getAllContacts(String currentId);


}
