package com.talkon.talkon.repositories.chat;

import com.talkon.talkon.entities.conversation.chat.Chat;
import com.talkon.talkon.projections.chat.ChatProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends AbstractRepository<Chat, String> {
    @Query(nativeQuery = true, value = """
            select (select case when :currentId = u_mentor.id then u_mentee.first_name else u_mentor.first_name end) as "firstName",
                   (select case when :currentId = u_mentor.id then u_mentee.last_name else u_mentor.last_name end)   as "lastName",
                   c.id                                                                                       as "chatId",
                   (select case when :currentId = u_mentor.id then u_mentee.id else u_mentor.id end)          as "userId",
                   (select case when :currentId = u_mentor.id then (case when u_mentee.is_online then null else u_mentee.last_seen end)
                   else (case when u_mentor.is_online then null else u_mentor.last_seen end ) end)   as "lastSeen",
                   (select case when :currentId = u_mentor.id then u_mentee.is_online else u_mentor.is_online end)   as "isOnline",
                   (select count(id)
                    from conversation.messages m
                    where m.chat_id = c.id
                      and not m.is_read)                                                                      as "newMessageCount",
                   (select case when :currentId = u_mentor.id then u_mentee.photo_path else u_mentor.photo_path end) as "photoPath"
            from conversation.chats c
                     join users.mentees on c.mentee_id = mentees.id
                     join users.mentors on c.mentor_id = mentors.id
                     join users.users u_mentee on mentees.user_id = u_mentee.id
                     join users.users u_mentor on mentors.user_id = u_mentor.id
            where u_mentee.id = :currentId
               or u_mentor.id = :currentId
               order by "newMessageCount" desc
               """)
    List<ChatProjection> getAllContacts(String currentId);


}
