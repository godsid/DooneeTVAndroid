package com.google.android.exoplayer.parser.aac;

import android.util.Log;

import com.google.android.exoplayer.hls.DefaultPacketAllocator;
import com.google.android.exoplayer.hls.Packet;
import com.google.android.exoplayer.hls.Parser;

import java.nio.ByteBuffer;

/**
 * Created by martin on 18/08/14.
 */
public class ADTSParser extends Parser{
  Packet currentPacket;
  Packet splitPacket;
  int state;
  private final int STATE_HEADER = 0;
  private final int STATE_DATA = 1;
  long pts;
  private boolean newPacket;
  private int frameLength;
  private long ptsOffset;
  private int sampleRate;

  public ADTSParser() {
  }

  private int getByte(ByteBuffer buffer, int position) {
    return ((int)buffer.get(position)) & 0xff;
  }

  @Override
  public Packet read() {
    if (currentPacket == null) {
      return null;
    }
    while (true) {
      int oldLimit = currentPacket.data.limit();

      if (splitPacket.data.limit() - splitPacket.data.position() < currentPacket.data.remaining()) {
        currentPacket.data.limit(currentPacket.data.position() + splitPacket.data.limit() - splitPacket.data.position());
      }

      splitPacket.data.put(currentPacket.data);
      currentPacket.data.limit(oldLimit);

      switch(state) {
        case STATE_HEADER:
          if (splitPacket.data.remaining() == 0) {
            int position = splitPacket.data.position() - 7;
            frameLength = (getByte(splitPacket.data, position + 3) & 0x3) << 11;
            frameLength += (getByte(splitPacket.data, position + 4) << 3);
            frameLength += (getByte(splitPacket.data, position + 5) & 0xe0) >> 5;

            if (sampleRate == 0) {
              sampleRate = AACExtractor.ADTSHeader.getSampleRate((getByte(splitPacket.data, position + 2) & 0x3c) >> 2);
            }
            int newLimit = splitPacket.data.position() + frameLength - 7;
            if (newLimit > splitPacket.data.capacity()) {
              DefaultPacketAllocator.resizePacket(splitPacket, 2 * newLimit);
            }

            splitPacket.data.limit(newLimit);
            state = STATE_DATA;
          }
          break;
        case STATE_DATA:
          if (splitPacket.data.remaining() == 0) {
            // new Packet || currentPacket.data.position() == currentPacket.data.limit()
            if (true) {
              Packet packet = splitPacket;
              splitPacket = DefaultPacketAllocator.getPacket(currentPacket.type);
              if (newPacket) {
                splitPacket.pts = currentPacket.pts;
                ptsOffset = 0;
                newPacket = false;
                //Log.d("ADTS", "newPacket " + splitPacket.pts);
              } else {
                ptsOffset += 1024*1000*45/sampleRate;
                splitPacket.pts = currentPacket.pts + ptsOffset;
                //Log.d("ADTS", "redPacket " + splitPacket.pts);
              }
              splitPacket.data.limit(7);
              state = STATE_HEADER;
              return packet;
            } else {

              int newLimit = splitPacket.data.position() + 7;
              if (newLimit > splitPacket.data.capacity()) {
                DefaultPacketAllocator.resizePacket(splitPacket, 2 * newLimit);
              }

              splitPacket.data.limit(newLimit);
              state = STATE_HEADER;
            }
          }
          break;
        default:
          break;
      }

      if (currentPacket.data.position() == currentPacket.data.limit()) {
        //currentPacket.release();
        return null;
      }
    }
  }

  @Override
  public void pushPacket(Packet packet) {
    packet.data.limit(packet.data.position());
    packet.data.position(0);
    currentPacket = packet;
    newPacket = true;
    if (splitPacket == null) {
      splitPacket = DefaultPacketAllocator.getPacket(Packet.TYPE_AUDIO);
      splitPacket.pts = currentPacket.pts;
      splitPacket.data.limit(7);
      state = STATE_HEADER;
    }
  }

  @Override
  public void release() {
    /*if (currentPacket != null) {
      currentPacket.release();
    }*/
  }
}
