package com.talkon.talkon.repositories.message;

import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.projections.message.LastMessageProjection;
import com.talkon.talkon.projections.message.MessageProjection;
import com.talkon.talkon.projections.message.ParentMessageProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends AbstractRepository<Message, String> {


    @Query(nativeQuery = true, value = """
            select * from (select id,
                                  body,
                                  created_at as "createdAt",
                                  is_read    as "isRead",
                                  from_id    as "senderId",
                                  file_path  as "fileLink",
                                  updated_at as "updatedAt",
                                  parent
                           from conversation.messages
                           where chat_id = :chatId
                           order by created_at desc
                           limit (case when (select count(id) from conversation.messages where chat_id=:chatId and not is_read)>:size
                           then (select count(id) from conversation.messages where chat_id=:chatId and not is_read) else :size end) offset :size*:page) inn order by "createdAt"
            """)
    List<MessageProjection> getAllMessages(String chatId, int size, long page);


    @Query(nativeQuery = true, value = """
            select id, body from conversation.messages where parent = :parentId
            """)
    ParentMessageProjection getParentMessage(String parentId);


    @Query(nativeQuery = true, value = """
            select messages.body,
                            messages.created_at as "createdAt"
                    from conversation.messages join conversation.chats c on c.id = messages.chat_id
                    where c.id = :chatId and messages.created_at = (select max(messages.created_at) from conversation.messages where chat_id = :chatId)
            """)
    LastMessageProjection getLastMessage(String chatId);

    @Query(nativeQuery = true, value = """
            select id,
                               body,
                               created_at as "createdAt",
                               is_read    as "isRead",
                               from_id    as "senderId",
                               file_path  as "fileLink",
                               updated_at as "updatedAt",
                               parent     as "parentId"
                        from conversation.messages
                        where messages.id = :id
            """)
    MessageProjection getMessageById(String id);

    @Modifying
    @Query(value = "update conversation.messages set is_read=true where id=:messageId", nativeQuery = true)
    void makeMessageAsRead(String messageId);
}
